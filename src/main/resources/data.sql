-- http://localhost:8088/Academy/h2-console

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR2(30) NOT NULL,
    surname VARCHAR2(30) NOT NULL,
    email VARCHAR2(30) NOT NULL UNIQUE,
    years INT NOT NULL
);

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR2(30) NOT NULL,
    description VARCHAR2(100),
    price DECIMAL(10, 2) NOT NULL,
    genre VARCHAR2(30) NOT NULL
);

CREATE TABLE orders (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  product_id INT,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE academy_logs (
  id INT PRIMARY KEY AUTO_INCREMENT,
  log_date TIMESTAMP,
  module VARCHAR2(30) NOT NULL,
  action VARCHAR2(30) NOT NULL,
  description VARCHAR2(100) NOT NULL,
  detail BLOB
);

-- INSERT
INSERT INTO users (name, surname, email, years) VALUES ('Maria', 'Bianchi', 'maria.bianchi@gmail.com', 30);
INSERT INTO users (name, surname, email, years) VALUES ('Giovanni', 'Verdi', 'giovanni.verdi@gmail.com', 18);
INSERT INTO users (name, surname, email, years) VALUES ('Luisa', 'Neri', 'luisa.neri@gmail.com', 15);
INSERT INTO users (name, surname, email, years) VALUES ('Marco', 'Esposito', 'marco.esposito@gmail.com', 12);
INSERT INTO users (name, surname, email, years) VALUES ('Laura', 'Rossi', 'laura.rossi@gmail.com', 10);
INSERT INTO users (name, surname, email, years) VALUES ('Paolo', 'Bianchi', 'paolo.bianchi@gmail.com', 17);
INSERT INTO users (name, surname, email, years) VALUES ('Francesca', 'Verdi', 'francesca.verdi@gmail.com', 19);
INSERT INTO users (name, surname, email, years) VALUES ('Antonio', 'Neri', 'antonio.neri@gmail.com', 20);
INSERT INTO users (name, surname, email, years) VALUES ('Luca', 'Esposito', 'luca.esposito@gmail.com', 25);
INSERT INTO users (name, surname, email, years) VALUES ('Simona', 'Bianchi', 'simona.bianchi@gmail.com', 60);

INSERT INTO products (name, description, price, genre) VALUES ('1984', 'Libro di George Orwell', 12.99, 'INTRATTENIMENTO');
INSERT INTO products (name, description, price, genre) VALUES ('Chianti Classico Riserva', 'Vino rosso toscano', 25.99, 'ALIMENTARI');
INSERT INTO products (name, description, price, genre) VALUES ('Maglietta Nike', 'Maglietta sportiva da uomo', 19.99, 'ABBIGLIAMENTO');
INSERT INTO products (name, description, price, genre) VALUES ('Scarpe da corsa Asics', 'Scarpe da corsa per donna', 89.99, 'ABBIGLIAMENTO');
INSERT INTO products (name, description, price, genre) VALUES ('FIFA 22', 'Videogioco di calcio per PS4', 59.99, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Cappello New Era', 'Cappello con logo New York Yankees', 29.99, 'ABBIGLIAMENTO');
INSERT INTO products (name, description, price, genre) VALUES ('Orologio da polso Seiko', 'Orologio automatico per uomo', 149.99, 'ABBIGLIAMENTO');
INSERT INTO products (name, description, price, genre) VALUES ('Borsa a tracolla Piquadro', 'Borsa in pelle per computer portatile', 199.99, 'ABBIGLIAMENTO');
INSERT INTO products (name, description, price, genre) VALUES ('Eau de Toilette Dolce&Gabbana', 'Profumo maschile con note agrumate', 79.99, 'BENESSERE');
INSERT INTO products (name, description, price, genre) VALUES ('Penna stilografica Montblanc', 'Penna di lusso in acciaio e resina', 299.99, 'BENESSERE');
INSERT INTO products (name, description, price, genre) VALUES ('iPhone 13', 'Ultimo modello di smartphone di Apple', 999.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Samsung Galaxy S21', 'Smartphone di fascia alta con fotocamera professionale', 899.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Dell XPS 13', 'Laptop ultraleggero e potente', 1299.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Lenovo ThinkPad X1 Carbon', 'Laptop aziendale di alta qualità', 1599.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Sony PlayStation 5', 'Ultima console di gioco di Sony', 499.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Nintendo Switch', 'Console di gioco ibrida portatile/fissa', 299.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Bose QuietComfort 35 II', 'Cuffie wireless con cancellazione del rumore', 299.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Samsung QN90A', 'Smart TV con display QLED e risoluzione 4K', 1499.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Amazon Echo Dot', 'Altoparlante intelligente con assistente vocale Alexa', 49.99, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Fitbit Charge 5', 'Smartwatch per il monitoraggio della salute e dell''attività fisica', 179.95, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Logitech MX Master 3', 'Mouse ergonomico per professionisti', 99.99, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Intel Core i9-12900K', 'Processore di fascia alta per PC desktop', 599.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('ASUS ROG Strix G17', 'Laptop gaming con schermo da 17 pollici e scheda video NVIDIA GeForce RTX 3070', 1999.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Canon EOS R5', 'Fotocamera mirrorless con risoluzione 8K', 3899.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Samsung T7 Touch', 'Unità SSD esterna con sensore di impronte digitali', 169.99, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Microsoft Surface Pro 8', 'Tablet professionale con processore Intel Core i7 e display da 13 pollici', 1399.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('LG UltraFine 5K', 'Monitor 5K da 27 pollici per Mac', 1299.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Adobe Creative Cloud', 'Suite di software creativi, tra cui Photoshop, Illustrator e InDesign', 52.99, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Ubiquiti UniFi Dream Machine', 'Router e switch di rete per utenti avanzati', 299.00, 'TECNOLOGIA');
INSERT INTO products (name, description, price, genre) VALUES ('Veeam Backup & Replication', 'Software di backup e ripristino per ambienti virtuali e cloud', 1175.00, 'TECNOLOGIA');

INSERT INTO orders (user_id, product_id) VALUES (1, 1);
INSERT INTO orders (user_id, product_id) VALUES (1, 2);
INSERT INTO orders (user_id, product_id) VALUES (1, 6);
INSERT INTO orders (user_id, product_id) VALUES (1, 7);
INSERT INTO orders (user_id, product_id) VALUES (1, 20);
INSERT INTO orders (user_id, product_id) VALUES (2, 1);
INSERT INTO orders (user_id, product_id) VALUES (3, 1);
INSERT INTO orders (user_id, product_id) VALUES (10, 29);
INSERT INTO orders (user_id, product_id) VALUES (10, 30);

INSERT INTO academy_logs (log_date, module, action, description, detail) VALUES ('2023-03-05T09:35:00+02:00', 'Academy', 'UPDATE', 'mock', null);
INSERT INTO academy_logs (log_date, module, action, description, detail) VALUES ('2023-03-06T20:59:25+02:00', 'Academy', 'CREATE', 'mock', null);
INSERT INTO academy_logs (log_date, module, action, description, detail) VALUES ('2023-03-03T15:32:12+02:00', 'Academy', 'UPDATE', 'mock', null);
INSERT INTO academy_logs (log_date, module, action, description, detail) VALUES ('2023-04-01T00:00:00+02:00', 'Academy', 'CREATE', 'mock', null);