CREATE TABLE "station" (
	"station_id" serial NOT NULL,
	"station_name" varchar(255) NOT NULL,
	CONSTRAINT "station_pk" PRIMARY KEY ("station_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "department" (
	"department_id" serial NOT NULL,
	"department_name" varchar(255) NOT NULL,
	CONSTRAINT "department_pk" PRIMARY KEY ("department_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "team" (
	"team_id" serial NOT NULL,
	"department_id" serial NOT NULL,
	CONSTRAINT "team_pk" PRIMARY KEY ("team_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "employee" (
	"employee_id" serial NOT NULL,
	"position" varchar(255) NOT NULL,
	"department_id" varchar(255) NOT NULL,
	"employment_date" DATE NOT NULL,
	"gender_id" integer(255) NOT NULL,
	"age" integer(255) NOT NULL,
	"children_count" integer(255) NOT NULL,
	"salary" FLOAT(255) NOT NULL,
	"team_id" integer(255) NOT NULL,
	"last_name" varchar(255) NOT NULL,
	CONSTRAINT "employee_pk" PRIMARY KEY ("employee_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "health_check" (
	"health_check_id" serial NOT NULL,
	"employee_id" integer NOT NULL,
	"health_check_date" DATE NOT NULL,
	CONSTRAINT "health_check_pk" PRIMARY KEY ("health_check_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "passenger" (
	"passenger_id" serial NOT NULL,
	"last_name" varchar(255) NOT NULL,
	"gender_id" integer(255) NOT NULL,
	"birthday" DATE NOT NULL,
	"ticket_id" integer NOT NULL,
	CONSTRAINT "passenger_pk" PRIMARY KEY ("passenger_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "path" (
	"path_id" serial NOT NULL,
	"start_station_id" integer NOT NULL,
	"end_station_id" integer NOT NULL,
	CONSTRAINT "path_pk" PRIMARY KEY ("path_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "locomotive" (
	"locomotive_id" serial NOT NULL,
	"team_id" integer NOT NULL,
	"station_id" integer NOT NULL,
	"production_date" DATE NOT NULL,
	"path_id" integer NOT NULL,
	"ride_count" integer NOT NULL,
	"ride_count_after_repair" integer NOT NULL,
	"repair_team_id" integer NOT NULL,
	CONSTRAINT "locomotive_pk" PRIMARY KEY ("locomotive_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "trip" (
	"trip_id" serial NOT NULL,
	"locomotive_id" integer NOT NULL,
	"path_id" integer NOT NULL,
	"direction" varchar(255) NOT NULL,
	"status" varchar(255) NOT NULL,
	"category" varchar(255) NOT NULL,
	"date" DATE NOT NULL,
	"is_abroad" BOOLEAN NOT NULL,
	CONSTRAINT "trip_pk" PRIMARY KEY ("trip_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "ticket" (
	"ticket_id" serial NOT NULL,
	"trip_id" integer NOT NULL,
	"baggage_given" BOOLEAN NOT NULL,
	"ticket_canceled" BOOLEAN NOT NULL,
	"order_date" DATETIME NOT NULL,
	"ticket_cancel_date" DATETIME,
	"from_station_id" integer NOT NULL,
	"to_station_id" integer NOT NULL,
	CONSTRAINT "ticket_pk" PRIMARY KEY ("ticket_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "technical_inspection" (
	"inspection_id" serial NOT NULL,
	"locomotive_id" integer NOT NULL,
	"inspection_date" DATE NOT NULL,
	"is_send_to_repair" BOOLEAN NOT NULL,
	"repair_deadline" DATE,
	CONSTRAINT "technical_inspection_pk" PRIMARY KEY ("inspection_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "gender" (
	"gender_id" serial NOT NULL,
	"gender_name" varchar(255) NOT NULL,
	CONSTRAINT "gender_pk" PRIMARY KEY ("gender_id")
) WITH (
  OIDS=FALSE
);





ALTER TABLE "team" ADD CONSTRAINT "team_fk0" FOREIGN KEY ("department_id") REFERENCES "department"("department_id");

ALTER TABLE "employee" ADD CONSTRAINT "employee_fk0" FOREIGN KEY ("department_id") REFERENCES "department"("department_id");
ALTER TABLE "employee" ADD CONSTRAINT "employee_fk1" FOREIGN KEY ("gender_id") REFERENCES "gender"("gender_id");
ALTER TABLE "employee" ADD CONSTRAINT "employee_fk2" FOREIGN KEY ("team_id") REFERENCES "team"("team_id");

ALTER TABLE "health_check" ADD CONSTRAINT "health_check_fk0" FOREIGN KEY ("employee_id") REFERENCES "employee"("employee_id");

ALTER TABLE "passenger" ADD CONSTRAINT "passenger_fk0" FOREIGN KEY ("gender_id") REFERENCES "gender"("gender_id");
ALTER TABLE "passenger" ADD CONSTRAINT "passenger_fk1" FOREIGN KEY ("ticket_id") REFERENCES "ticket"("ticket_id");

ALTER TABLE "path" ADD CONSTRAINT "path_fk0" FOREIGN KEY ("start_station_id") REFERENCES "station"("station_id");
ALTER TABLE "path" ADD CONSTRAINT "path_fk1" FOREIGN KEY ("end_station_id") REFERENCES "station"("station_id");

ALTER TABLE "locomotive" ADD CONSTRAINT "locomotive_fk0" FOREIGN KEY ("team_id") REFERENCES "team"("team_id");
ALTER TABLE "locomotive" ADD CONSTRAINT "locomotive_fk1" FOREIGN KEY ("station_id") REFERENCES "station"("station_id");
ALTER TABLE "locomotive" ADD CONSTRAINT "locomotive_fk2" FOREIGN KEY ("path_id") REFERENCES "path"("path_id");
ALTER TABLE "locomotive" ADD CONSTRAINT "locomotive_fk3" FOREIGN KEY ("repair_team_id") REFERENCES "team"("team_id");

ALTER TABLE "trip" ADD CONSTRAINT "trip_fk0" FOREIGN KEY ("locomotive_id") REFERENCES "locomotive"("locomotive_id");
ALTER TABLE "trip" ADD CONSTRAINT "trip_fk1" FOREIGN KEY ("path_id") REFERENCES "path"("path_id");

ALTER TABLE "ticket" ADD CONSTRAINT "ticket_fk0" FOREIGN KEY ("trip_id") REFERENCES "trip"("trip_id");
ALTER TABLE "ticket" ADD CONSTRAINT "ticket_fk1" FOREIGN KEY ("from_station_id") REFERENCES "station"("station_id");
ALTER TABLE "ticket" ADD CONSTRAINT "ticket_fk2" FOREIGN KEY ("to_station_id") REFERENCES "station"("station_id");

ALTER TABLE "technical_inspection" ADD CONSTRAINT "technical_inspection_fk0" FOREIGN KEY ("locomotive_id") REFERENCES "locomotive"("locomotive_id");


