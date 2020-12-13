-- Table: public.vehicles

-- DROP TABLE public.vehicles;

CREATE TABLE public.vehicles
(
    car_maker type_carmaker NOT NULL,
    model character varying(32) COLLATE pg_catalog."default" NOT NULL,
    model_year integer NOT NULL,
    vin character varying(17) COLLATE pg_catalog."default" NOT NULL,
    fuel_type etype_fuel,
    license_plate character varying COLLATE pg_catalog."default" NOT NULL
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