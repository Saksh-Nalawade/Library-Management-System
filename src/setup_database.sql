-- Create Books table
CREATE TABLE Books (
    id NUMBER PRIMARY KEY,
    title VARCHAR2(100) NOT NULL,
    author VARCHAR2(100) NOT NULL,
    category VARCHAR2(50),
    quantity NUMBER DEFAULT 0
);


-- Create sequence for book IDs
CREATE SEQUENCE book_id_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Create trigger to auto-increment book ID
CREATE OR REPLACE TRIGGER book_id_trigger
    BEFORE INSERT ON Books
    FOR EACH ROW
BEGIN
    SELECT book_id_seq.NEXTVAL
    INTO :NEW.id
    FROM dual;
END;
/

-- Create IssuedBooks table
CREATE TABLE IssuedBooks (
    issue_id NUMBER PRIMARY KEY,
    book_id NUMBER NOT NULL,
    student_id NUMBER NOT NULL,
    issue_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    fine NUMBER DEFAULT 0,
    FOREIGN KEY (book_id) REFERENCES Books(id)
);

-- Create sequence for issue IDs
CREATE SEQUENCE issue_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Create trigger to auto-increment issue ID
CREATE OR REPLACE TRIGGER issue_id_trigger
    BEFORE INSERT ON IssuedBooks
    FOR EACH ROW
BEGIN
    SELECT issue_seq.NEXTVAL
    INTO :NEW.issue_id
    FROM dual;
END;
/ 