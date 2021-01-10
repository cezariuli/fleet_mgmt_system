-- Table: public.vehicles

-- DROP TABLE public.vehicles;

CREATE TABLE public.vehicles
(
    car_maker type_carmaker NOT NULL,
    model character varying(32) COLLATE pg_catalog."default" NOT NULL,
    model_year integer NOT NULL,
    vin character varying(17) COLLATE pg_catalog."default" NOT NULL,
    fuel_type etype_fuel,
    license_plate character varying COLLATE pg_catalog."default" NOT NULL,
    odometer integer,
    transmission character varying(10) COLLATE pg_catalog."default",
    power smallint,
    fuel_consumption real,
    body character varying COLLATE pg_catalog."default",
    no_of_passengers smallint,
    luggage smallint,
    no_of_doors smallint,
    co2 smallint,
    air_cond character varying COLLATE pg_catalog."default",
    navigation boolean
)

TABLESPACE pg_default;

ALTER TABLE public.vehicles
    OWNER to cezar;

COMMENT ON COLUMN public.vehicles.model
IS 'Model of the car.
i.e.: Logan, A4, 320d etc';

COMMENT ON COLUMN public.vehicles.model_year
IS 'Construction year of the care';

COMMENT ON COLUMN public.vehicles.vin
IS 'Vehicle Identification Number';

COMMENT ON COLUMN public.vehicles.license_plate
IS 'Registration number or the license plate of the vehicle.';

COMMENT ON COLUMN public.vehicles.transmission
IS 'Transmission type: MANUAL or AUTOMATIC';

COMMENT ON COLUMN public.vehicles.power
IS 'Power in kW';

COMMENT ON COLUMN public.vehicles.fuel_consumption
IS 'Mixed fuel consumption [ l / 100km ].';

COMMENT ON COLUMN public.vehicles.body
IS 'Body type.
i.e.: Hatchback, Sedan, Coupe etc.';

COMMENT ON COLUMN public.vehicles.no_of_passengers
IS 'Passenger capacity';

COMMENT ON COLUMN public.vehicles.luggage
IS 'Luggage capacity in the trunk.
1 medium sized luggage
2 medium sized luggage
3 medium sized luggage  etc';

COMMENT ON COLUMN public.vehicles.no_of_doors
IS 'Doors number: 3 or 5';

COMMENT ON COLUMN public.vehicles.co2
IS 'CO2 emmission [ g/km ].';

COMMENT ON COLUMN public.vehicles.air_cond
IS 'Air Conditioner:
automatic
manual
none';

COMMENT ON COLUMN public.vehicles.navigation
IS 'Navigation present? Yes or No';