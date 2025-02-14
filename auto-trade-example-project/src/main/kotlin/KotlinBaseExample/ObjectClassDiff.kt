package org.example.KotlinBaseExample

class ObjectClassDiff {
    val student = Student

    fun testObject() {
       print(student.name + " " + student.age + " " + student.isStudent)
    }

}