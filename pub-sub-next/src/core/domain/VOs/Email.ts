import regex from "@/util/regex";
import EntityInvalid from "../errors/EntityValidation";

export default class Email {
  private _value: string;
  private constructor(email: string) {
    this._value = email;
  }

  static of(email: string) {
    if (Email.validate(email)) {
      return new Email(email);
    }
    throw new EntityInvalid("Email not is valid");
  }

  private static validate(email: string) {
    return regex.VALID_EMAIL.test(email);
  }
  public get value() {
    return this._value;
  }
}
