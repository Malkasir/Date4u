//package com.tutego.date4u.interfaces;
//
//import com.tutego.date4u.core.profile.Profile;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//
//@ShellComponent
//public class EntityManagerCommands {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @ShellMethod("Find a profile by its nickname")
//    public String findprofile(Long id) {
//        try {
////            Profile profile = entityManager.createQuery(
////                            "SELECT p FROM Profile p WHERE p.nickname = :nickname", Profile.class)
////                    .setParameter("nickname", nickname)
////                    .getSingleResult();
//            Profile profile = entityManager.find(Profile.class, id);
//
//            return profile.toString();
//        } catch (Exception e) {
//            return "Profile not found or an error occurred: " + e.getMessage();
//        }
//    }
//}
