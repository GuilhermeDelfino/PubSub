import regex from "@/util/regex";
import { InputValidator } from "./InputValidator";
import strings from "@/util/strings";

export class InputTextValidator implements InputValidator {
  constructor(
    private readonly required?: boolean,
    private readonly min?: number,
    private readonly max?: number,
    private readonly canContainsNumber: boolean = true
  ) {}

  validate(value: string): string[] {
    const errors: string[] = [];
    const { MAX_SIZE, MIN_SIZE, REQUIRED, NOT_CONTAINS_NUMBER } =
      strings.validation;
    if (this.required) {
      if (!value) {
        errors.push(REQUIRED);
      }
    }
    if (this.min) {
      if (value.length < this.min) {
        errors.push(MIN_SIZE.replace("{min}", this.min.toString()));
      }
    }
    if (this.max) {
      if (value.length > this.max) {
        errors.push(MAX_SIZE.replace("{max}", this.max.toString()));
      }
    }
    if (this.canContainsNumber) {
      if (regex.CONTAINS_NUMBER.test(value)) {
        errors.push(NOT_CONTAINS_NUMBER);
      }
    }
    return errors;
  }
}
