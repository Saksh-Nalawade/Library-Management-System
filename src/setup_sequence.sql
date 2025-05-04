-- Drop existing sequence and trigger if they exist
BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE Sakshi.book_id_seq';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TRIGGER Sakshi.book_id_trigger';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -4080 THEN
            RAISE;
        END IF;
END;
/

-- Create sequence for book IDs in Sakshi schema
CREATE SEQUENCE Sakshi.book_id_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Create trigger to auto-increment book ID
CREATE OR REPLACE TRIGGER Sakshi.book_id_trigger
    BEFORE INSERT ON Sakshi.Books
    FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT Sakshi.book_id_seq.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END IF;
END;
/

-- Test the sequence
INSERT INTO Sakshi.Books (title, author, category, quantity)
VALUES ('Test Book', 'Test Author', 'Test Category', 1);

COMMIT; 