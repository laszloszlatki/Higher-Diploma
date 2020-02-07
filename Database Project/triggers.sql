
--
-- Create table to Table structure for table 'past_Students'
--

CREATE TABLE IF NOT EXISTS past_Student( 
 student_id char(8) NOT NULL, 
 eircode char(6) default NULL,
 first_name varchar(35) default NULL, 
 last_name varchar(35) default NULL, 
 fees_paid double default NULL, 
 DOB date default NULL, 
 PRIMARY KEY(student_id)
 );

--
-- trigger: if a student record is deleted and not in enrollment, 
-- move records to past_Student table.
--

delimiter //
CREATE TRIGGER delete_student
BEFORE DELETE ON student
FOR EACH ROW BEGIN

IF(OLD.student_id NOT IN (SELECT student_id FROM enrollment)) THEN
	 
		CALL studentAddToPast_student(OLD.student_id, OLD.eircode, OLD.first_name, OLD.last_name, OLD.DOB);
        
     END IF;
   END;//
delimiter ;

--
-- --------------------------------------------------------------
--

-- procedure to insert record to past_student table

delimiter //
CREATE PROCEDURE studentAddToPast_student(
	IN a CHAR(8), 
	IN b CHAR(6),
	IN c VARCHAR(35),
	IN d VARCHAR(35),
	IN e date
)
BEGIN
  INSERT INTO past_students(student_id, eircode, first_name, last_name, DOB)
		VALUES(a,b,c,d,e);
END;//

delimiter ;

--
-- --------------------------------------------------------------
--


