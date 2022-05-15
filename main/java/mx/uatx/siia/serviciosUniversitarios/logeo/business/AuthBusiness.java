package mx.uatx.siia.serviciosUniversitarios.logeo.business;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import mx.uatx.siia.serviciosUniversitarios.dto.SessionTO;
import mx.uatx.siia.serviciosUniversitarios.enums.SeveridadMensajeEnum;
import mx.uatx.siia.serviciosUniversitarios.logeo.dao.AuthDAO;

@Service
@Configurable
public class AuthBusiness implements Serializable {

	/**
	 * serialVersionUID
	 */
	
	@Autowired
	private AuthDAO authDAO;
	
	private static final long serialVersionUID = 3296618443018487900L;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public ResultadoTO iniciarSesion(final String strUsuario, final String strContrasena) {

		final ResultadoTO res = new ResultadoTO(true);

		try {
			final SessionTO sesion = authDAO.iniciarSesion(strUsuario, obtenerCadenaMd5(strContrasena));

			if (null == sesion) {
				res.agregarMensaje(SeveridadMensajeEnum.ERROR, "comun.msj.iniciar.sesion.credenciales.incorrectas");
				res.setBlnValido(false);
			} else {
				sesion.setStrLoginUsuarioLog(strUsuario);
				res.setObjeto(sesion);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			res.agregarMensaje(SeveridadMensajeEnum.ERROR, "comun.msj.iniciar.sesion.credenciales.incorrectas");
			res.setBlnValido(false);
		}

		return res;
	}


	private String obtenerCadenaMd5(final String password) throws NoSuchAlgorithmException {

		final MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());

		final byte byteData[] = md.digest();

		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++)
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

		return sb.toString();
	}

	public ResultadoTO registroBitacora(SessionTO sesion) {

		final ResultadoTO resultado = new ResultadoTO(true);
		try {
			authDAO.registroBitacora(sesion);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultado.agregarMensaje(SeveridadMensajeEnum.ALERTA, "comun.label.sumario.msj.usuario.error.bitacora");
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public ResultadoTO insertarRoles(SessionTO sesion) {

		ResultadoTO resultado = new ResultadoTO(true);
		try {
			resultado = authDAO.validarInicioSesion(sesion.getStrLoginUsuarioLog());	
			sesion.setListPrivilegios((List<String>) resultado.getObjeto());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultado.setBlnValido(false);
		}
		
		if (sesion.getListPrivilegios().isEmpty()) {
			resultado.agregarMensaje(SeveridadMensajeEnum.ALERTA, "comun.label.sumario.msj.usuario.sin.privilegios");
		}
		
		sesion.getListPrivilegios().add("USUARIO_ESTA_LOGUEADO");	

		return resultado;
	}
	

}
