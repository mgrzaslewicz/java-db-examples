create table if not exists USER_ACCOUNT
(
    user_account_id VARCHAR(36) PRIMARY KEY NOT NULL,
    first_name      VARCHAR(36)             NOT NULL,
    last_name       VARCHAR(36)             NOT NULL
);

create table if not exists USER_SUBSCRIPTION
(
    user_account_id   VARCHAR(36)   NOT NULL,
    subscription_code VARCHAR(1024) NOT NULL,
    valid_from        TIMESTAMP     NOT NULL,
    valid_to          TIMESTAMP     NOT NULL,
    insert_time       TIMESTAMP     NOT NULL,
    update_time       TIMESTAMP DEFAULT NULL,
    constraint fk_user_subscription_user_account_id FOREIGN KEY (user_account_id) references USER_ACCOUNT (user_account_id),
    constraint uq_user_subscription_user_account_id_subscription_code unique (user_account_id, subscription_code)
);

INSERT INTO USER_ACCOUNT (user_account_id, first_name, last_name)
VALUES ('cd4f697e-f271-4112-bd0b-1b2ebf6682b4', 'John', 'Rambo');

INSERT INTO USER_SUBSCRIPTION (user_account_id, subscription_code, valid_from, valid_from, valid_to, insert_time, update_time)
VALUES ('cd4f697e-f271-4112-bd0b-1b2ebf6682b4', 'monthly-product-subscription', NOW(), NOW() + interval '24 hours', NOW(), null);