import regex from "@/util/regex";
import EntityInvalid from "../errors/EntityValidation";

export default class Name {
  private _value: string;
  private constructor(name: string) {
    this._value = name;
  }

  static of(name: string) {
    if (Name.validate(name)) {
      return new Name(name);
    }
    throw new EntityInvalid(
      "Name must have between 3 and 50 characters and not contains numbers"
    );
  }

  private static validate(name: string) {
    return regex.VALID_NAME.test(name);
  }
  public get value() {
    return this._value;
  }
}
