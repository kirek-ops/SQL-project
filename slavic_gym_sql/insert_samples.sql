-- Insert sample data into gyms
INSERT INTO gyms (id_gym, name, street, city, postal_code, contact_number) VALUES
                                                                               (1, 'Fitness Pro', '123 Main St', 'Springfield', '12345', '555-1234'),
                                                                               (2, 'Health Hub', '456 Elm St', 'Shelbyville', '54321', '555-5678');

-- Insert sample data into employees
INSERT INTO employees (id_employee, first_name, last_name) VALUES
                                                               (1, 'John', 'Doe'),
                                                               (2, 'Jane', 'Smith'),
                                                               (3, 'Emily', 'Johnson');

-- -- Insert sample data into gym_managers
-- INSERT INTO gym_managers (id_gym, id_manager, start_date, end_date) VALUES
--                                                                         (1, 1, '2020-01-01', NULL),
--                                                                         (2, 2, '2021-01-01', NULL);
--
-- -- Insert sample data into positions
-- INSERT INTO positions (id_position, position_name) VALUES
--                                                        (1, 'Trainer'),
--                                                        (2, 'Manager');
--
-- -- Insert sample data into employee_positions
-- INSERT INTO employee_positions (id_employee, id_position, start_date, end_date) VALUES
--                                                                                     (1, 1, '2020-01-01', NULL),
--                                                                                     (2, 2, '2021-01-01', NULL);
--
-- -- Insert sample data into gym_members
-- INSERT INTO gym_members (id_member, first_name, last_name, email, phone_number, join_date, passwd) VALUES
--                                                                                                        (1, 'Alice', 'Brown', 'alice.brown@example.com', '555-2345', '2023-01-01', 'password123'),
--                                                                                                        (2, 'Bob', 'Davis', 'bob.davis@example.com', '555-3456', '2023-02-01', 'password456');
--
-- -- Insert sample data into member_employees
-- INSERT INTO member_employees (id_member, id_employee) VALUES
--                                                           (1, 1),
--                                                           (2, 2);
--
-- -- Insert sample data into classes
-- INSERT INTO classes (id_class, class_name, schedule, time_from, time_till, id_gym, capacity, id_instructor) VALUES
--                                                                                                                 (1, 'Yoga', '2023-06-01 10:00:00', '10:00:00', '11:00:00', 1, 20, 3),
--                                                                                                                 (2, 'Pilates', '2023-06-02 11:00:00', '11:00:00', '12:00:00', 2, 15, 3);
--
-- -- Insert sample data into bookings
-- INSERT INTO bookings (id_booking, id_member, id_class) VALUES
--                                                            (1, 1, 1),
--                                                            (2, 2, 2);
--
-- -- Insert sample data into categories
-- INSERT INTO categories (id_category, category_name, parent_category_id) VALUES
--                                                                             (1, 'Equipment', NULL),
--                                                                             (2, 'Supplements', NULL),
--                                                                             (3, 'Weights', 1);
--
-- -- Insert sample data into inventory
-- INSERT INTO inventory (id_item, item_name, quantity, id_gym, id_category) VALUES
--                                                                               (1, 'Dumbbell', 10, 1, 3),
--                                                                               (2, 'Protein Powder', 20, 2, 2);
--
-- -- Insert sample data into products_categories
-- INSERT INTO products_categories (id_item, id_category) VALUES
--                                                            (1, 3),
--                                                            (2, 2);
--
-- -- Insert sample data into memberships
-- INSERT INTO memberships (id_membership, is_active, price, duration) VALUES
--                                                                         (1, TRUE, 49.99, 30),
--                                                                         (2, TRUE, 129.99, 90);
--
-- -- Insert sample data into client_membership
-- INSERT INTO client_membership (id_member, id_membership, start_date) VALUES
--                                                                          (1, 1, '2023-01-01'),
--                                                                          (2, 2, '2023-02-01');
--
-- -- Insert sample data into transactions_memberships
-- INSERT INTO transactions_memberships (id_transaction, id_membership, id_member, order_time) VALUES
--                                                                                                 (1, 1, 1, '2023-01-01 10:00:00'),
--                                                                                                 (2, 2, 2, '2023-02-01 11:00:00');
--
-- -- Insert sample data into transactions_inventory
-- INSERT INTO transactions_inventory (id_transaction, id_item, id_member, order_time, quantity) VALUES
--                                                                                                   (1, 1, 1, '2023-01-01 10:00:00', 2),
--                                                                                                   (2, 2, 2, '2023-02-01 11:00:00', 1);
--
-- -- Insert sample data into access_codes
-- INSERT INTO access_codes (code_id, generated_at, id_member, first_used_at) VALUES
--                                                                                ('code123', '2023-01-01 10:00:00', 1, '2023-01-01 10:05:00'),
--                                                                                ('code456', '2023-02-01 11:00:00', 2, '2023-02-01 11:05:00');
--
-- -- Insert sample data into visits
-- INSERT INTO visits (id_visit, id_member, visit_time) VALUES
--                                                          (1, 1, '2023-01-01 10:05:00'),
--                                                          (2, 2, '2023-02-01 11:05:00');
--
-- -- Insert sample data into repetition_exercises
-- INSERT INTO repetition_exercises (id_exercise, exercise_name) VALUES
--                                                                   (1, 'Push-ups'),
--                                                                   (2, 'Pull-ups');
--
-- -- Insert sample data into time_exercises
-- INSERT INTO time_exercises (id_exercise, exercise_name) VALUES
--                                                             (1, 'Plank'),
--                                                             (2, 'Running');
--
-- -- Insert sample data into repetition_goals
-- INSERT INTO repetition_goals (id_goal, id_member, id_exercise, target_reps) VALUES
--                                                                                 (1, 1, 1, 100),
--                                                                                 (2, 2, 2, 50);
--
-- -- Insert sample data into time_goals
-- INSERT INTO time_goals (id_goal, id_member, id_exercise, target_time) VALUES
--                                                                           (1, 1, 1, '00:05:00'),
--                                                                           (2, 2, 2, '00:30:00');
--
--
-- -- Insert sample data into exercise_logs_repetitions
-- INSERT INTO exercise_logs_repetitions (id_log, id_member, id_goal, log_date, reps_done) VALUES
--                                                                                             (1, 1, 1, '2023-01-01 10:00:00', 20),
--                                                                                             (2, 2, 2, '2023-02-01 11:00:00', 10);
--
-- -- Insert sample data into exercise_logs_time
-- INSERT INTO exercise_logs_time (id_log, id_member, id_goal, log_date, time_done) VALUES
--                                                                                      (1, 1, 1, '2023-01-01 10:00:00', '00:01:00'),
--                                                                                      (2, 2, 2, '2023-02-01 11:00:00', '00:10:00');
