package mx.uatx.siia.serviciosUniversitarios.logeo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import mx.uatx.siia.serviciosUniversitarios.dto.SessionTO;
import mx.uatx.siia.serviciosUniversitarios.modelo.SiBitSesiones;
import mx.uatx.siia.serviciosUniversitarios.modelo.SiPaMensajesValidacion;
import mx.uatx.siia.serviciosUniversitarios.modelo.SiPaPermisos;

@Repository
public class AuthDAO implements Serializable {


	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7362046618896753259L;

	protected final  transient Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext(unitName = "SIIA")
	private EntityManager em;

	/**
	 * Método que verifica en base de datos si el usuario es válido para iniciar sesión en el sistema
	 * 
	 * @return SessionTO
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public SessionTO iniciarSesion(final String strUsuario, final String strContrasena) throws Exception {
		SessionTO sesUsuario = null;

		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("RECHUM.RHPGSIIA.PRAUTUSUARIO",
				SiPaPermisos.class);
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);//P_LOGIN IN VARCHAR
		storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);//P_PASS IN VARCHAR
		storedProcedure.registerStoredProcedureParameter(3, void.class, ParameterMode.OUT);//P_RESULTADO OUT VARCHAR
		storedProcedure.registerStoredProcedureParameter(4, void.class, ParameterMode.OUT);//P_IDPER OUT NUMBER
		storedProcedure.registerStoredProcedureParameter(5, void.class, ParameterMode.OUT);//P_NOMBRE OUT VARCHAR
		storedProcedure.registerStoredProcedureParameter(6, void.class, ParameterMode.OUT);//P_HISTACAD OUT
		storedProcedure.registerStoredProcedureParameter(7, void.class, ParameterMode.OUT);//P_HISTLAB OUT
		storedProcedure.registerStoredProcedureParameter(8, void.class, ParameterMode.OUT);//P_GRIUPO OUT

		storedProcedure.setParameter(1, strUsuario);
		storedProcedure.setParameter(2, strContrasena);
		
		storedProcedure.execute();

		String intValido = storedProcedure.getOutputParameterValue(3).toString();
		final boolean blnValido = (null != intValido && "false" != intValido);
		
		if (blnValido) {
			
			sesUsuario = new SessionTO(Long.parseLong(storedProcedure.getOutputParameterValue(4).toString()), 
					storedProcedure.getOutputParameterValue(5).toString(),
					storedProcedure.getOutputParameterValue(6) == null?0:Long.parseLong(storedProcedure.getOutputParameterValue(6).toString()),
					storedProcedure.getOutputParameterValue(7) == null?0:Long.parseLong(storedProcedure.getOutputParameterValue(7).toString()),
					storedProcedure.getOutputParameterValue(8) == null?"Ninguno":(storedProcedure.getOutputParameterValue(8).toString()));

			sesUsuario.setBlnEsCumpleanios(false);
		}

		return sesUsuario;
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public ResultadoTO validarInicioSesion(final String strUsuario) throws Exception {
		final ResultadoTO res = new ResultadoTO();
		final List<String> lstPermisos = new ArrayList<>();

		final StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SIIUAT.SIPGSIIA.PRVALINICIARSESIONREST",
				SiPaMensajesValidacion.class, SiPaPermisos.class);
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);//P_LOGIN_IN_VARCHAR
		storedProcedure.registerStoredProcedureParameter(2, Boolean.class, ParameterMode.OUT);//P_VALIDO_IN_NUMBER
		storedProcedure.registerStoredProcedureParameter(3, void.class, ParameterMode.REF_CURSOR);//P_RESULTADOS_OUT_SYS_REFCURSOR
		storedProcedure.registerStoredProcedureParameter(4, void.class, ParameterMode.REF_CURSOR);//P_PERMISOS_OUT_SYS_REFCURSOR

		storedProcedure.setParameter(1, strUsuario);
		
		storedProcedure.execute();

		@SuppressWarnings("unchecked")
		List<SiPaMensajesValidacion> resqMensajes = storedProcedure.getResultList();
		
		@SuppressWarnings("unchecked")
		List<SiPaPermisos> resqPermisos = storedProcedure.getResultList();
		
		final Object obj = storedProcedure.getOutputParameterValue(2);
		if (obj!=null) {
			res.setBlnValido((boolean) obj);
		}
		
		res.setearListadoMensajesValidacion(resqMensajes);

		if (null != resqPermisos) {
			for (SiPaPermisos it : resqPermisos) {
				lstPermisos.add(it.getStrPermiso());
			}
		}

		res.setObjeto(lstPermisos);
		return res;
	}
	
	/**
	 * Método que recupera los privilegios del usuario que se logueo
	 * 
	 * @return List<String>
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<String> obtenerPrivilegiosUsuarioLog(final String strUsuarioLog) {
		List<String> lstResultado = new ArrayList<>();

		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SIIUAT.SIPGSIIA.PROBTENERPERMISOS",
				SiPaPermisos.class);
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);//V_IDPERSONA_IN_NUMBER
		storedProcedure.registerStoredProcedureParameter(2, void.class, ParameterMode.REF_CURSOR);//P_RESULTADOS_OUT_SYS_REFCURSOR

		storedProcedure.setParameter(1, strUsuarioLog);

		@SuppressWarnings("unchecked")
		List<SiPaPermisos> resq = storedProcedure.getResultList();

		if (null != resq) {
			for (SiPaPermisos it : resq) {
				lstResultado.add(it.getStrPermiso());
			}
		}

		return lstResultado;
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void registroBitacora(final SessionTO session) throws Exception {
		
		final SiBitSesiones sesionEntidad = new SiBitSesiones();
		BeanUtils.copyProperties(session, sesionEntidad);
		sesionEntidad.setFcSesion(new Date());
		em.persist(sesionEntidad);
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public ResultadoTO obtenerUsuarioPass(final String strUsuario) throws Exception {
		final ResultadoTO res = new ResultadoTO(true);
		
		final StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SIIUAT.SIPGSIIA.PROBTPASSWORDUSUARIO",
				SiPaMensajesValidacion.class, SiPaPermisos.class);
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);//P_LOGIN_IN_VARCHAR
		storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);//OUT_CONTRASENA_IN_NUMBER

		storedProcedure.setParameter(1, strUsuario);
		
		storedProcedure.execute();
		
		final Object obj = storedProcedure.getOutputParameterValue(2);
		if (obj!=null) {
			res.setObjeto((String) obj);
		} else {
			res.setBlnValido(false);
		}
		return res;
	}

}