export class Car {

    constructor(id, chassisCode, make, model, year, cubicCapacity){
        this.id = id;
        this.chassisCode = chassisCode;
        this.make = make;
        this.model = model;
        this.year = year;
        this.cubicCapacity = cubicCapacity;
        this.isNew = true;
    }

    getID(){
        return this.id;
    }

    setID(id){
        this.id = id;
    }

    getchassisCode() {
        return this.chassisCode;
    }

    setchassisCode(value) {
        this.chassisCode = value;
    }

    getmake() {
        return this.make;
    }

    setmake(value) {
        this.make = value;
    }

    getmodel() {
        return this.model;
    }

    setmodel(value) {
        this.model = value;
    }

    getyear() {
        return this.year;
    }

    setyear(value) {
        this.year = value;
    }

    getcubicCapacity() {
        return this.cubicCapacity;
    }

    setcubicCapacity(value) {
        this.cubicCapacity = value;
    }

    getisNew() {
        return this.isNew;
    }

    setIsNew(value) {
        this.isNew = value;
    }
}