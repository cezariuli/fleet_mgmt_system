-- Type: etype_fuel

-- DROP TYPE public.etype_fuel;

CREATE TYPE public.etype_fuel AS ENUM
    ('Petrol', 'Diesel', 'Petrol + LPG', 'Petrol + Electric', 'Petrol + LPG + Electric', 'Diesel + Electric', 'Electric');

ALTER TYPE public.etype_fuel
    OWNER TO cezar;

-- Type: type_carmaker

-- DROP TYPE public.type_carmaker;

CREATE TYPE public.type_carmaker AS ENUM
    ('Alfa Romeo', 'Audi', 'BMW', 'Chevrolet', 'Citroen', 'Dacia', 'Fiat', 'Ford', 'Honda', 'Hyundai', 'Jeep', 'Kia',
    'Land Rover', 'Lexus', 'Mazda', 'Mercedes-Benz', 'Mini', 'Mitsubishi', 'Nissan', 'Opel', 'Peugeot', 'Porsche',
    'Renault', 'Seat', 'Skoda', 'Smart', 'Suzuki', 'Tesla', 'Toyota', 'Volkswagen');

ALTER TYPE public.type_carmaker
    OWNER TO cezar;

COMMENT ON TYPE public.type_carmaker
    IS 'List of possible carmakers. ';

-- Type: type_daciamodels

-- DROP TYPE public.type_daciamodels;

CREATE TYPE public.type_daciamodels AS
(
	"Dokker" text,
	"Duster" text,
	"Lodgy" text,
	"Logan" text,
	"Logan Stepway" text,
	"Sandero" text,
	"Sandero Stepway" text,
	"Spring" text
);

ALTER TYPE public.type_daciamodels
    OWNER TO cezar;

COMMENT ON TYPE public.type_daciamodels
    IS 'All Dacia models.';