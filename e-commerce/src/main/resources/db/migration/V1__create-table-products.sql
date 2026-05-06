CREATE TABLE products (
                          id UUID PRIMARY KEY,
                          title VARCHAR(150) NOT NULL,
                          description TEXT,
                          price NUMERIC(10, 2) NOT NULL,
                          image_url VARCHAR(255),
                          affiliate_link VARCHAR(500) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);