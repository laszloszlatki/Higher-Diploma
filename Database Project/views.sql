--
-- List all students, who gets score > 70 and 
-- enrolled to at least two subject.
--

CREATE VIEW Multiple_Subject_score_over_70 AS
SELECT student_id
FROM exam_results
WHERE score >= 70
GROUP BY student_id
HAVING COUNT(student_id) > 1;

--
-- -----------------------------------------------
--

--
-- List of students who passed their exam in a descending order
--

CREATE VIEW Best_Students AS 
SELECT s.student_id, s.first_name,s.last_name, e.score FROM student AS s 
INNER JOIN exam_results AS e ON s.student_id = e.student_id GROUP BY student_id 
HAVING score >=40 ORDER BY score DESC;

--
-- -----------------------------------------------
--

--
-- Students progressing to next year
-- List of students, who passed their exam and paid full fee (6500)
-- 

CREATE VIEW Welcome_Nxt_Year AS
SELECT s.student_id, s.first_name, s.last_name, s.fees_paid, e.score
FROM student AS s INNER JOIN exam_results AS e
ON s.student_id = e.student_id
GROUP BY student_id
HAVING score >=40 AND fees_paid>=6500;

--
-- -----------------------------------------------
--


--
-- Indexes necessary for the optimal performance of the queries in the views
--

CREATE INDEX scores ON exam_results(score);
CREATE INDEX studentIDs ON Students(student_id);



