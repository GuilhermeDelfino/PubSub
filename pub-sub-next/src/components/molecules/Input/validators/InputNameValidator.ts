import strings from "@/util/strings";
import regex from "@/util/regex";
import { InputValidator } from "./InputValidator";

export class InputNameValidator implements InputValidator {
  private static INSTANCE: null | InputNameValidator = null;
  static getInstance() {
    if (!this.INSTANCE) this.INSTANCE = new InputNameValidator();
    return this.INSTANCE;
  }
  private constructor() {}
  validate(value: string): string[] {
    const { INVALID, IS_EMPTY } = strings.createSubscriber.inputs.name.errors;

    const errors = [];
    if (!value.length) {
      errors.push(IS_EMPTY);
    } else if (!regex.VALID_NAME.test(value)) {
      errors.push(INVALID);
    }
    return errors;
  }
}
