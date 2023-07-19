SELECT *
FROM instructor LEFT OUTER JOIN student
ON instructor.id = student.advisor_id;

SELECT *
FROM instructor LEFT OUTER JOIN lecture
ON instructor.id = lecture.instructor_id;

SELECT *
FROM student FULL OUTER JOIN instructor
ON student.advisor_id = instructor.id;

SELECT *
FROM student
INNER JOIN enrolling_lectures
ON student.id = enrolling_lectures.student_id
INNER JOIN lecture
ON enrolling_lectures.lecture_id = lecture.id;

SELECT *
FROM student
LEFT OUTER JOIN enrolling_lectures
ON student.id = enrolling_lectures.student_id
LEFT OUTER JOIN lecture
ON enrolling_lectures.lecture_id = lecture.id

-- 화요일에 강의를 하는 교수님만 뽑아 보자
SELECT lecture.name, instructor.first_name, instructor.last_name
FROM lecture INNER JOIN instructor
ON lecture.instructor_id = instructor.id
WHERE lecture.day = 'tue';

SELECT s.id AS student_id, s.first_name, s.last_name
FROM student AS s
INNER JOIN (
    SELECT el.student_id, l.name
    FROM enrolling_lectures el
    JOIN lecture l on el.lecture_id = l.id
    WHERE l.instructor_id = 2
) subQuery ON s.id = subQuery.student_id;