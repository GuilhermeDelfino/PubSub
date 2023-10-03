import Email from "../VOs/Email";
import Name from "../VOs/Name";

export default class Subscriber {
    private _name: Name;
    private _email: Email;

    constructor(name: Name, email: Email){
        this._name = name;
        this._email = email;
    }

    public get name() {
        return this._name;
    }
    public get email() {
        return this._email;
    }
}