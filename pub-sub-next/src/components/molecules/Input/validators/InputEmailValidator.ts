import strings from "@/util/strings";
import regex from "@/util/regex";
import { InputValidator } from "./InputValidator";

export class InputEmailValidator implements InputValidator {
  private static INSTANCE: InputValidator | null = null;
  static getInstance(): InputValidator {
    if (!this.INSTANCE) this.INSTANCE = new InputEmailValidator();
    return this.INSTANCE;
  }
  private constructor() {}
  validate(value: string): string[] {
    const { INVALID, IS_EMPTY } = strings.createSubscriber.inputs.email.errors;
    const errors = [];
    if (!value.length) {
      errors.push(IS_EMPTY);
    } else if (!regex.VALID_EMAIL.test(value)) {
      errors.push(INVALID);
    }
    return errors;
  }
}
