
DROP TABLE adds IF EXISTS;

CREATE TABLE adds (
  id         INTEGER IDENTITY PRIMARY KEY,
  add_title VARCHAR(80),
  add_description  VARCHAR(255),
  category    VARCHAR(30),
  type_of_add       VARCHAR(30),
  price  VARCHAR(30),
  room_number  VARCHAR(30),
  contact_name  VARCHAR(30),
  contact_surname  VARCHAR(30),
  telephone  VARCHAR(30)
);
CREATE INDEX adds_id ON adds (id);



