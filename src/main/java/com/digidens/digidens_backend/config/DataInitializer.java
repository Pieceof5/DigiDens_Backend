package com.digidens.digidens_backend.config;



import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.digidens.digidens_backend.model.Course;
import com.digidens.digidens_backend.model.CourseInstance;
import com.digidens.digidens_backend.model.TaskCardCell;
import com.digidens.digidens_backend.model.TaskCardColumn;
import com.digidens.digidens_backend.model.TaskCardRow;
import com.digidens.digidens_backend.model.TaskCardTemplate;
import com.digidens.digidens_backend.model.User;
import com.digidens.digidens_backend.repository.CourseInstanceRepository;
import com.digidens.digidens_backend.repository.CourseRepository;
import com.digidens.digidens_backend.repository.TaskCardTemplateRepository;
import com.digidens.digidens_backend.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(
            UserRepository userRepository,
            CourseRepository courseRepository,
            CourseInstanceRepository courseInstanceRepository,
            TaskCardTemplateRepository templateRepository
            ) {
        return args -> {

            if(userRepository.count() == 0) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                // ==== ADMIN ====
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                admin.setFirstname("Admin");
                admin.setLastname("User");
                admin.setGradeLevel("N/A");
                userRepository.save(admin);

                // ==== TEACHERS ====
                User teacher1 = new User("teacher1", passwordEncoder.encode("password123"), "TEACHER", "Maija", "Erkola", "N/A");
                userRepository.save(teacher1);

                User teacher2 = new User("teacher2", passwordEncoder.encode("password123"), "TEACHER", "Jaakko", "Sippola", "N/A");
                userRepository.save(teacher2);

                // ==== STUDENTS ====
                User student1 = new User("student1", passwordEncoder.encode("password123"), "STUDENT", "Matti", "Heinonen", "1");
                student1.setStudentNumber("S0001"); userRepository.save(student1);

                User student2 = new User("student2", passwordEncoder.encode("password123"), "STUDENT", "Veera", "Nukari", "1");
                student2.setStudentNumber("S0002"); userRepository.save(student2);

                User student3 = new User("student3", passwordEncoder.encode("password123"), "STUDENT", "Oskari", "Laine", "2");
                student3.setStudentNumber("S0003"); userRepository.save(student3);

                User student4 = new User("student4", passwordEncoder.encode("password123"), "STUDENT", "Liisa", "Virtanen", "2");
                student4.setStudentNumber("S0004"); userRepository.save(student4);

                User student5 = new User("student5", passwordEncoder.encode("password123"), "STUDENT", "Janne", "Korhonen", "1");
                student5.setStudentNumber("S0005"); userRepository.save(student5);

                User student6 = new User("student6", passwordEncoder.encode("password123"), "STUDENT", "Sanna", "Mäkinen", "3");
                student6.setStudentNumber("S0006"); userRepository.save(student6);

                User student7 = new User("student7", passwordEncoder.encode("password123"), "STUDENT", "Eero", "Lehtonen", "2");
                student7.setStudentNumber("S0007"); userRepository.save(student7);

                User student8 = new User("student8", passwordEncoder.encode("password123"), "STUDENT", "Anni", "Hakala", "1");
                student8.setStudentNumber("S0008"); userRepository.save(student8);

                User student9 = new User("student9", passwordEncoder.encode("password123"), "STUDENT", "Petri", "Salmi", "3");
                student9.setStudentNumber("S0009"); userRepository.save(student9);

                User student10 = new User("student10", passwordEncoder.encode("password123"), "STUDENT", "Kaisa", "Rantanen", "2");
                student10.setStudentNumber("S0010"); userRepository.save(student10);

                // ==== EXISTING COURSES ====
                Course course1 = new Course();
                course1.setCourseCode("DENT-0002");
                course1.setName("Kirurgia");
                course1.setGradeLevel("2");
                courseRepository.save(course1);

                Course course2 = new Course();
                course2.setCourseCode("DENT-0003");
                course2.setName("Hammaslääketieteen perusteet");
                course2.setGradeLevel("1");
                courseRepository.save(course2);

                Course course3 = new Course();
                course3.setCourseCode("DENT-0005");
                course3.setName("Testikurssi ilman osallistujia");
                course3.setGradeLevel("N/A");
                courseRepository.save(course3);

                // ==== NEW COURSES ====
                Course course4 = new Course();
                course4.setCourseCode("DENT-0010");
                course4.setName("Kariologia");
                course4.setGradeLevel("1");
                courseRepository.save(course4);

                Course course5 = new Course();
                course5.setCourseCode("DENT-0011");
                course5.setName("Endodontia");
                course5.setGradeLevel("2");
                courseRepository.save(course5);

                Course course6 = new Course();
                course6.setCourseCode("DENT-0012");
                course6.setName("Ortodoksia");
                course6.setGradeLevel("1");
                courseRepository.save(course6);

                Course course7 = new Course();
                course7.setCourseCode("DENT-0013");
                course7.setName("Prostodontia");
                course7.setGradeLevel("3");
                courseRepository.save(course7);

                Course course8 = new Course();
                course8.setCourseCode("DENT-0014");
                course8.setName("Parodontologia");
                course8.setGradeLevel("2");
                courseRepository.save(course8);

                // ==== COURSE INSTANCES ====
                // Existing course instances
                CourseInstance ci1 = new CourseInstance();
                ci1.setCourse(course1);
                ci1.setTeacher(teacher1);
                ci1.getStudents().add(student3);
                ci1.getStudents().add(student4);
                ci1.getStudents().add(student7);
                ci1.getStudents().add(student10);
                ci1.setInstanceCode(course1.getCourseCode() + "-0001");
                ci1.setStartDate(LocalDate.of(2025, 1, 10));
                ci1.setEndDate(LocalDate.of(2025, 5, 30));
                courseInstanceRepository.save(ci1);

                CourseInstance ci2 = new CourseInstance();
                ci2.setCourse(course2);
                ci2.setTeacher(teacher2);
                ci2.getStudents().add(student1);
                ci2.getStudents().add(student2);
                ci2.getStudents().add(student5);
                ci2.getStudents().add(student8);
                ci2.setInstanceCode(course2.getCourseCode() + "-0001");
                ci2.setStartDate(LocalDate.of(2025, 1, 10));
                ci2.setEndDate(LocalDate.of(2025, 5, 30));
                courseInstanceRepository.save(ci2);

                CourseInstance ci3 = new CourseInstance();
                ci3.setCourse(course3);
                ci3.setTeacher(null);
                ci3.setInstanceCode(course3.getCourseCode() + "-0001");
                ci3.setStartDate(LocalDate.of(2025, 1, 10));
                ci3.setEndDate(LocalDate.of(2025, 5, 30));
                courseInstanceRepository.save(ci3);

                // New course instances
                CourseInstance ci4 = new CourseInstance();
                ci4.setCourse(course4);
                ci4.setTeacher(teacher1);
                ci4.getStudents().add(student1);
                ci4.getStudents().add(student2);
                ci4.getStudents().add(student5);
                ci4.getStudents().add(student8);
                ci4.setInstanceCode(course4.getCourseCode() + "-0001");
                ci4.setStartDate(LocalDate.of(2025, 2, 1));
                ci4.setEndDate(LocalDate.of(2025, 6, 15));
                courseInstanceRepository.save(ci4);

                CourseInstance ci5 = new CourseInstance();
                ci5.setCourse(course5);
                ci5.setTeacher(teacher2);
                ci5.getStudents().add(student3);
                ci5.getStudents().add(student4);
                ci5.getStudents().add(student7);
                ci5.getStudents().add(student10);
                ci5.setInstanceCode(course5.getCourseCode() + "-0001");
                ci5.setStartDate(LocalDate.of(2025, 3, 1));
                ci5.setEndDate(LocalDate.of(2025, 7, 15));
                courseInstanceRepository.save(ci5);

                CourseInstance ci6 = new CourseInstance();
                ci6.setCourse(course6);
                ci6.setTeacher(teacher1);
                ci6.getStudents().add(student1);
                ci6.getStudents().add(student2);
                ci6.getStudents().add(student5);
                ci6.getStudents().add(student8);
                ci6.setInstanceCode(course6.getCourseCode() + "-0001");
                ci6.setStartDate(LocalDate.of(2025, 2, 15));
                ci6.setEndDate(LocalDate.of(2025, 6, 30));
                courseInstanceRepository.save(ci6);

                CourseInstance ci7 = new CourseInstance();
                ci7.setCourse(course7);
                ci7.setTeacher(teacher2);
                ci7.getStudents().add(student3);
                ci7.getStudents().add(student4);
                ci7.getStudents().add(student7);
                ci7.getStudents().add(student10);
                ci7.setInstanceCode(course7.getCourseCode() + "-0001");
                ci7.setStartDate(LocalDate.of(2025, 3, 10));
                ci7.setEndDate(LocalDate.of(2025, 7, 31));
                courseInstanceRepository.save(ci7);

                CourseInstance ci8 = new CourseInstance();
                ci8.setCourse(course8);
                ci8.setTeacher(teacher1);
                ci8.getStudents().add(student1);
                ci8.getStudents().add(student2);
                ci8.getStudents().add(student5);
                ci8.getStudents().add(student8);
                ci8.setInstanceCode(course8.getCourseCode() + "-0001");
                ci8.setStartDate(LocalDate.of(2025, 4, 1));
                ci8.setEndDate(LocalDate.of(2025, 8, 15));
                courseInstanceRepository.save(ci8);

                // Link course instances to courses
                course1.getCourseInstances().add(ci1);
                course2.getCourseInstances().add(ci2);
                course3.getCourseInstances().add(ci3);
                course4.getCourseInstances().add(ci4);
                course5.getCourseInstances().add(ci5);
                course6.getCourseInstances().add(ci6);
                course7.getCourseInstances().add(ci7);
                course8.getCourseInstances().add(ci8);

                courseRepository.save(course1);
                courseRepository.save(course2);
                courseRepository.save(course3);
                courseRepository.save(course4);
                courseRepository.save(course5);
                courseRepository.save(course6);
                courseRepository.save(course7);
                courseRepository.save(course8);
                
         


             // ==== TEST TASK CARD TEMPLATE (säilyttää olemassa olevan datan) ====
                TaskCardTemplate testTemplate = new TaskCardTemplate();
                testTemplate.setName("H1 Kasvot suu hampaat Morfologian suoritteet kevät 2025 - Testidata");

                // Liitetään samaan kurssitoteutukseen (esim. ci1)
                testTemplate.setCourseInstance(ci1);

                // Sarakkeet
                TaskCardColumn tcol1 = new TaskCardColumn();
                tcol1.setName("Tehtävä");
                tcol1.setType("TEXT");
                tcol1.setRole("TEACHER");
                tcol1.setTemplate(testTemplate);

                TaskCardColumn tcol2 = new TaskCardColumn();
                tcol2.setName("Päivämäärä");
                tcol2.setType("TEXT");
                tcol2.setRole("TEACHER");
                tcol2.setTemplate(testTemplate);

                TaskCardColumn tcol3 = new TaskCardColumn();
                tcol3.setName("Arvio");
                tcol3.setType("MULTIPLE_CHOICE");
                tcol3.setRole("STUDENT");
                tcol3.setOptions(List.of("Hyvä", "Ok", "Huono"));
                tcol3.setTemplate(testTemplate);

                TaskCardColumn tcol4 = new TaskCardColumn();
                tcol4.setName("Hyväksytty");
                tcol4.setType("CHECKBOX");
                tcol4.setRole("TEACHER");
                tcol4.setTemplate(testTemplate);

                testTemplate.setColumns(List.of(tcol1, tcol2, tcol3, tcol4));

                // Rivit ja solut
                TaskCardRow trow1 = new TaskCardRow();
                trow1.setName("Potilas A");
                trow1.setTemplate(testTemplate);

                TaskCardCell tcell1 = new TaskCardCell();
                tcell1.setColumnName("Tehtävä");
                tcell1.setCellValue("Poista hammaskivi potilaalta A");
                tcell1.setRow(trow1);

                TaskCardCell tcell2 = new TaskCardCell();
                tcell2.setColumnName("Päivämäärä");
                tcell2.setCellValue("2025-12-01");
                tcell2.setRow(trow1);

                TaskCardCell tcell3 = new TaskCardCell();
                tcell3.setColumnName("Arvio");
                tcell3.setCellValue("Ok"); // Oletusvalinta
                tcell3.setRow(trow1);

                TaskCardCell tcell4 = new TaskCardCell();
                tcell4.setColumnName("Hyväksytty");
                tcell4.setCellValue("false");
                tcell4.setRow(trow1);

                trow1.setCells(List.of(tcell1, tcell2, tcell3, tcell4));
                testTemplate.setRows(List.of(trow1));

                // Tallennetaan testitemplate
                templateRepository.save(testTemplate);


                System.out.println("Mock data tallennettu onnistuneesti uusien kurssien ja toteutusten kanssa.");
            }
        };
    }
}


