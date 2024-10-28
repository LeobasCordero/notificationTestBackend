-- Insert users
INSERT INTO user (name, email, phone_number) VALUES
    ('Leo Cordero', 'lcordero@gila.com', '1234567890'),
    ('Mary Martinez', 'mmartinez@gmail.com', '0987654321'),
    ('Benji', 'benji@gmail.com', '654321654');

-- Insert subscribed categories
INSERT INTO user_category (user_id, category_id) VALUES (1, 1);  -- Leo Cordero subscribed to sports
INSERT INTO user_category (user_id, category_id) VALUES (1, 2);  -- Leo Cordero subscribed to finance
INSERT INTO user_category (user_id, category_id) VALUES (2, 3);  -- Mary Martinez subscribed to movies

-- Insert subscribed channels
INSERT INTO user_channel (user_id, channel_id) VALUES (1, 1);  -- Leo Cordero uses email
INSERT INTO user_channel (user_id, channel_id) VALUES (1, 2);  -- Leo Cordero uses push notification
INSERT INTO user_channel (user_id, channel_id) VALUES (2, 3);  -- Mary Martinez uses sms

-- Insert log of messages sent
INSERT INTO message (user_id, category_id, channel_id, content, sent_at)
VALUES (1, 1, 1, 'Sports update via Email', CURRENT_TIMESTAMP);

INSERT INTO message (user_id, category_id, channel_id, content, sent_at)
VALUES (1, 2, 2, 'Finance alert via Push Notification', CURRENT_TIMESTAMP);

INSERT INTO message (user_id, category_id, channel_id, content, sent_at)
VALUES (2, 3, 3, 'Movie release via SMS', CURRENT_TIMESTAMP);



