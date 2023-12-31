package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

//    student DB
    private final Map<String, Student> studentDb = new HashMap<>();
//    teacher DB
    private final Map<String, Teacher> teacherDb = new HashMap<>();
//    combine DB
    private final Map<String, String> teach_stuDb = new HashMap<>();

    public void addStudent(Student student) {
        studentDb.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if (studentDb.containsKey(student) && teacherDb.containsKey(teacher)) {
            teach_stuDb.put(teacher, student);
        }
    }

    public Student getStudentByName(String name) {
        return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDb.get((name));
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> list = new ArrayList<>();
        for (String key: teach_stuDb.keySet()) {
            if (key.equals(teacher)) {
                list.add(teach_stuDb.get(key));
            }
        }
        return list;
    }

    public List<String> getAllStudents() {
        List<String> list = new ArrayList<>();
        for (String key: studentDb.keySet()) {
            list.add(key);
        }
        return list;
    }

    public void deleteTeacherByName(String teacher) {
        String teacherTo_student = teach_stuDb.get(teacher);
        teach_stuDb.remove(teacher);
        studentDb.remove(teacherTo_student);
        teacherDb.remove(teacher);
    }

    public void deleteAllTeachers() {
        for (String key: teacherDb.keySet()) {
            teacherDb.remove(key);
            String stud = teach_stuDb.get(key);
            teach_stuDb.remove(key);
            studentDb.remove(stud);
        }
    }
//    done
}