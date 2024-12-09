CREATE TABLE department (
                            department_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

CREATE TABLE employee (
                          employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          first_name VARCHAR(255) NOT NULL,
                          last_name VARCHAR(255) NOT NULL,
                          position VARCHAR(255),
                          salary INT,
                          department_id BIGINT,
                          CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES department(department_id)
);
