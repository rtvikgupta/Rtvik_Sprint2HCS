export class RequestAppointment {
    appointmentId:String;
    dateTime: String;
    status:boolean;
    userId: String;
    centerId: String;
    testId: String;
    

    constructor(appointmentId:String,dateTime:String,status:boolean,userId:String,centerId:String,testId:String){
        this.appointmentId = appointmentId;
        this.dateTime = dateTime;
        this.userId = userId;
        this.centerId = centerId;
        this.testId = testId;
        this.status = status;
    }
}
