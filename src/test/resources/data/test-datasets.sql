DELETE FROM customer_contact;

INSERT INTO customer_contact (id, first_name, last_name, email, address_line1, address_line2, city, state, zip_code,
                              created_date)
VALUES (1, 'John', 'Doe', 'jd@gmail.com', '12001 E 42TH ST', NULL, 'New York', 'NY', '12345', '2021-01-01'),
       (2, 'Miki', 'Cohen', 'mc@gmail.com', '16002 W 57TH ST', NULL, 'New York', 'NY', '14006', '2021-02-01');