/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.Juego;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Categoria;
import service.exceptions.IllegalOrphanException;
import service.exceptions.NonexistentEntityException;

/**
 *
 * @author luisvasquez
 */
public class CategoriaService implements Serializable {

    public CategoriaService() {
         this.emf = Persistence.createEntityManagerFactory("LAB02_JPA_P_UNIT");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categoria categoria) {
        if (categoria.getJuegoList() == null) {
            categoria.setJuegoList(new ArrayList<Juego>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Juego> attachedJuegoList = new ArrayList<Juego>();
            for (Juego juegoListJuegoToAttach : categoria.getJuegoList()) {
                juegoListJuegoToAttach = em.getReference(juegoListJuegoToAttach.getClass(), juegoListJuegoToAttach.getIdJuego());
                attachedJuegoList.add(juegoListJuegoToAttach);
            }
            categoria.setJuegoList(attachedJuegoList);
            em.persist(categoria);
            for (Juego juegoListJuego : categoria.getJuegoList()) {
                Categoria oldIdCategoriaOfJuegoListJuego = juegoListJuego.getIdCategoria();
                juegoListJuego.setIdCategoria(categoria);
                juegoListJuego = em.merge(juegoListJuego);
                if (oldIdCategoriaOfJuegoListJuego != null) {
                    oldIdCategoriaOfJuegoListJuego.getJuegoList().remove(juegoListJuego);
                    oldIdCategoriaOfJuegoListJuego = em.merge(oldIdCategoriaOfJuegoListJuego);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getIdCategoria());
            List<Juego> juegoListOld = persistentCategoria.getJuegoList();
            List<Juego> juegoListNew = categoria.getJuegoList();
            List<String> illegalOrphanMessages = null;
            for (Juego juegoListOldJuego : juegoListOld) {
                if (!juegoListNew.contains(juegoListOldJuego)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Juego " + juegoListOldJuego + " since its idCategoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Juego> attachedJuegoListNew = new ArrayList<Juego>();
            for (Juego juegoListNewJuegoToAttach : juegoListNew) {
                juegoListNewJuegoToAttach = em.getReference(juegoListNewJuegoToAttach.getClass(), juegoListNewJuegoToAttach.getIdJuego());
                attachedJuegoListNew.add(juegoListNewJuegoToAttach);
            }
            juegoListNew = attachedJuegoListNew;
            categoria.setJuegoList(juegoListNew);
            categoria = em.merge(categoria);
            for (Juego juegoListNewJuego : juegoListNew) {
                if (!juegoListOld.contains(juegoListNewJuego)) {
                    Categoria oldIdCategoriaOfJuegoListNewJuego = juegoListNewJuego.getIdCategoria();
                    juegoListNewJuego.setIdCategoria(categoria);
                    juegoListNewJuego = em.merge(juegoListNewJuego);
                    if (oldIdCategoriaOfJuegoListNewJuego != null && !oldIdCategoriaOfJuegoListNewJuego.equals(categoria)) {
                        oldIdCategoriaOfJuegoListNewJuego.getJuegoList().remove(juegoListNewJuego);
                        oldIdCategoriaOfJuegoListNewJuego = em.merge(oldIdCategoriaOfJuegoListNewJuego);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoria.getIdCategoria();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getIdCategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Juego> juegoListOrphanCheck = categoria.getJuegoList();
            for (Juego juegoListOrphanCheckJuego : juegoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categoria (" + categoria + ") cannot be destroyed since the Juego " + juegoListOrphanCheckJuego + " in its juegoList field has a non-nullable idCategoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Categoria findCategoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
