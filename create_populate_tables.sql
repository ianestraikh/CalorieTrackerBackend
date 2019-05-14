/*Creating User table:*/
create table usr (
    user_id int not null constraint user_pk primary key 
        generated always as identity (start with 1, increment by 1),
    fname varchar(50) not null,
    lname varchar(50) not null,
    email varchar(50) not null,
    dob date not null,
    height decimal(9,2) not null,
    weight decimal(9,2) not null,
    gender char(1) not null,
    address varchar(100),
    postcode char(4),
    levelofactivity int not null,
    stepspermile int not null,
    constraint check_user_gender check (gender in ('F', 'M')),
    constraint check_level_of_activity check (levelofactivity between 1 and 5),
    constraint user_email_unique unique (email)
);

/*Creating Credential table:*/
create table credential (
    cred_id int not null constraint cred_pk primary key
        generated always as identity (start with 1, increment by 1),
    username varchar(50) not null constraint cred_username_unique unique,
    user_id int not null constraint cred_user_fk references usr(user_id),
    password_hash varchar(128) not null,
    signup_date date not null
);

/*Creating Food table:*/
create table food (
    food_id int not null constraint food_pk primary key
        generated always as identity (start with 1, increment by 1),
    food_name varchar(100) not null,
    food_category varchar(50) not null,
    calorie_amount decimal(9,4) not null,
    serving_unit varchar(10) not null,
    serving_amount decimal(9,4) not null,
    fat decimal(9,4) not null
);

/*Creating Consumption table:*/
create table consumption (
    consum_id int not null constraint consumption_pk primary key
        generated always as identity (start with 1, increment by 1),
    user_id int not null constraint consum_user_fk references usr(user_id),
    consumption_date date not null,
    food_id int not null constraint consum_food_fk references food(food_id),
    quantity int not null,
    constraint consum_unique unique (user_id, consumption_date, food_id)
);

/*Creating Report table:*/
create table report (
    report_id int not null constraint report_pk primary key
        generated always as identity (start with 1, increment by 1),
    user_id int not null constraint report_user_fk references usr(user_id),
    report_date date not null,
    calories_consumed decimal(9,4) not null,
    calories_burned decimal(9,4) not null,
    steps_taken int not null,
    calorie_goal decimal(9,4) not null,
    constraint report_unique unique (user_id, report_date)
);

/*Populating User table:*/
insert into
    usr (
        fname,
        lname, 
        email, 
        dob, 
        height,  
        weight, 
        gender, 
        address,
        postcode, 
        levelofactivity, 
        stepspermile
    ) 
    values (
        'John',
        'Green',
        'johngreen@gmail.com', 
        '1973-03-16', 
        180, 
        70.2, 
        'M', 
        'Malvern East 20 Dandenong Road', 
        '3145', 
        1,
        1500
    );
insert into usr (fname, lname, email, dob, height, weight, gender, address, postcode, levelofactivity, stepspermile) 
    values ('Bill', 'Graham', 'billgraham@gmail.com', '1982-01-01', 176, 65, 'M', 'Malvern East 758 Dandenong Road', '3145', 3, 1800);   
insert into usr (fname, lname, email, dob, height, weight, gender, address, postcode, levelofactivity, stepspermile) 
    values ('Anna', 'Lucia', 'annalucia@gmail.com', '1994-05-01', 180, 60, 'F', 'Carnegie 105 Carnegie Road', '3045', 5, 2500);
insert into usr (fname, lname, email, dob, height, weight, gender, address, postcode, levelofactivity, stepspermile) 
    values ('Marta', 'Black', 'martablack@gmail.com', '1987-12-31', 170, 70, 'F', 'Caulfield 1087 Caulfield Road', '3089', 2, 1600);
insert into usr (fname, lname, email, dob, height, weight, gender, address, postcode, levelofactivity, stepspermile) 
    values ('Lucy', 'Johnson', 'lucyjohnson@gmail.com', '1998-05-01', 165, 58, 'F', 'Clayton 58 College Way', '3788', 4, 2300);

/*Populating Credential table:*/
insert into credential(username, user_id, password_hash, signup_date) 
    values(
        'john1973',
        1,       'E0B50E3ADB85CE07A41196709BA642886BA828A354ACEE42EAE7559BC7C623981897F56020699ED61FA052F4784BF37E76EFF016EE065D77BC158DD172EABD76',
        '2019-03-16'
    );  
insert into credential(username, user_id, password_hash, signup_date) 
    values(
        'billy007',
        2,
'E0B50E3ADB85CE07A41196709BA642886BA828A354ACEE42EAE7559BC7C623981897F56020699ED61FA052F4784BF37E76EFF016EE065D77BC158DD172EABD76',
        '2019-03-16'
    ); 
insert into credential(username, user_id, password_hash, signup_date) 
    values(
        'annathebest',
        3,
'E0B50E3ADB85CE07A41196709BA642886BA828A354ACEE42EAE7559BC7C623981897F56020699ED61FA052F4784BF37E76EFF016EE065D77BC158DD172EABD76',
        '2019-03-16'
    );  
insert into credential(username, user_id, password_hash, signup_date) 
    values(
        'marta',
        4,
'E0B50E3ADB85CE07A41196709BA642886BA828A354ACEE42EAE7559BC7C623981897F56020699ED61FA052F4784BF37E76EFF016EE065D77BC158DD172EABD76',
        '2019-03-16'
    );   
insert into credential(username, user_id, password_hash, signup_date) 
    values(
        'lucy',
        5,
'E0B50E3ADB85CE07A41196709BA642886BA828A354ACEE42EAE7559BC7C623981897F56020699ED61FA052F4784BF37E76EFF016EE065D77BC158DD172EABD76',
        '2019-03-16'
    );

/*Population Food table:*/
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Bacon pork', 'meat', 36, 'slice', 1, 3);    
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Banana, fresh, 8" long', 'fruit', 96, 'each', 1, 0);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Bread, white or whole wheat: regular', 'bread', 70, 'slice', 1, 1);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Beer (1 can = 12 fl oz): regular, malt, or no alcohol', 'drink', 148, 'can', 1, 0);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Milk whole', 'drink', 150, 'cup', 1, 8);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Mustard', 'condiment', 12, 'Tbsp', 1, 1);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Orange, fresh 2 5/8" diam', 'fruit', 62, 'each', 1, 0);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Orange drink', 'drink', 117, 'cup', 1, 0);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Nuts: walnuts', 'nuts', 161, 'cup', 0.25, 15);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Pasta, plain: linguine, macaroni or spaghetti', 'noodle', 197, 'cup', 1, 1);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Salmon, canned, drained', 'fish', 118, 'oz', 3, 5);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('American cheese', 'dairy', 144, 'oz', 1, 9);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Cereal, cold: cornflakes', 'cereal', 110, 'cup', 1, 0);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Chicken, light meat only', 'meat', 47, 'oz', 1, 1);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Chicken, dark meat only', 'meat', 56, 'oz', 1, 2);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Beef, canned', 'meat', 166, 'cup', 0.5, 10);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Butter regular', 'dairy', 102, 'Tbsp', 1, 12);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Apple, 2 3/4" diam', 'fruit', 81, 'each', 1, 0);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Apple, cider or juice, unsweetened', 'drink', 87, 'cup', 0.75, 0);
insert into food(food_name, food_category, calorie_amount, serving_unit, serving_amount, fat) 
    values('Rice white', 'grain', 103, 'cup', 0.5, 0);

/*Populating Consumption table:*/
insert into consumption(user_id, consumption_date, food_id, quantity) 
    values (1, '2019-03-16', 20, 3);
insert into consumption(user_id, consumption_date, food_id, quantity) 
    values (1, '2019-03-16', 12, 5);  
insert into consumption(user_id, consumption_date, food_id, quantity) 
    values (1, '2019-03-16', 1, 4);
insert into consumption(user_id, consumption_date, food_id, quantity) 
    values (2, '2019-03-16', 2, 10);
insert into consumption(user_id, consumption_date, food_id, quantity) 
    values (2, '2019-03-16', 5, 5);


/*Populating Report table:*/
insert into report (user_id, report_date, calories_consumed, calories_burned, steps_taken, calorie_goal) 
    values (1, '2019-03-24', 2448, 2000, 2000, 100);
insert into report (user_id, report_date, calories_consumed, calories_burned, steps_taken, calorie_goal) 
    values (1, '2019-03-25', 2320, 1894, 2000, 100);
insert into report (user_id, report_date, calories_consumed, calories_burned, steps_taken, calorie_goal) 
    values (1, '2019-03-26', 2420, 1900, 2000, 100);
insert into report (user_id, report_date, calories_consumed, calories_burned, steps_taken, calorie_goal) 
    values (2, '2019-03-24', 1898, 1600, 2000, 100);
insert into report (user_id, report_date, calories_consumed, calories_burned, steps_taken, calorie_goal) 
    values (2, '2019-03-25', 1895, 1600, 2000, 100);
insert into report (user_id, report_date, calories_consumed, calories_burned, steps_taken, calorie_goal) 
    values (2, '2019-03-26', 1789, 1230, 2000, 100);
insert into report (user_id, report_date, calories_consumed, calories_burned, steps_taken, calorie_goal) 
    values (3, '2019-03-24', 1999, 1500, 2000, 100);
