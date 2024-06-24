//package com.tutego.date4u.interfaces;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.shell.standard.ShellOption;
//
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.springframework.shell.command.invocation.InvocableShellMethod.log;
//
//@ShellComponent
//public class JdbcCommands {
//
//    private final JdbcTemplate jdbcTemplate;
//    private final Logger log = LoggerFactory.getLogger( getClass() );
//
//    @Autowired
//    public JdbcCommands(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @ShellMethod("Get the horn length of a unicorn profile based on nickname")
//    public String hornlength(@ShellOption String nickname) {
//        String sql = "SELECT hornlength FROM Profile WHERE nickname = ?";
//        List<Integer> lengths = jdbcTemplate.queryForList(sql, Integer.class, nickname);
//
//        Optional<Integer> length = lengths.stream().findFirst();
//
//        if (length.isPresent()) {
//            log.info(length.get().toString());
//            return length.get().toString();
//        } else {
//            return "Unknown profile for nickname " + nickname;
//        }
//    }
//}