-- Table: public.bookings

-- DROP TABLE public.bookings;

CREATE TABLE public.bookings
(
    id uuid NOT NULL,
    car character varying COLLATE pg_catalog."default",
    start_date date,
    end_date date,
    status character varying COLLATE pg_catalog."default",
    client character varying COLLATE pg_catalog."default",
    comment character varying COLLATE pg_catalog."default",
    CONSTRAINT bookings_pkey PRIMARY KEY (id),
    CONSTRAINT car FOREIGN KEY (car)
        REFERENCES public.vehicles (license_plate) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.bookings
    OWNER to cezar;

COMMENT ON COLUMN public.bookings.id
IS 'Booking id';

COMMENT ON COLUMN public.bookings.start_date
IS 'Start date of the booking.';

COMMENT ON COLUMN public.bookings.end_date
IS 'End date of the booking';

COMMENT ON COLUMN public.bookings.status
IS 'Current status of the booking.';

COMMENT ON COLUMN public.bookings.comment
IS 'Section to add comments/observations about current booking.';