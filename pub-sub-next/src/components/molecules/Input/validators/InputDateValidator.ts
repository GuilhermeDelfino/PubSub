import strings from "@/util/strings";
import { InputValidator } from "./InputValidator";
import { formatDate, trimDate } from "@/util/date";

export class InputDateValidator implements InputValidator {
  constructor(
    private readonly required: boolean = true,
    private readonly minDate?: Date,
    private readonly maxDate?: Date
  ) {}

  validate(value: string): string[] {
    const errors: string[] = [];
    const dateValue = trimDate(new Date(value));
    const { DATE_MIN_INVALID, DATE_REQUIRED, DATE_MAX_INVALID } =
      strings.validation;
    if (this.required) {
      if (!value) {
        errors.push(DATE_REQUIRED);
      }
    }
    if (this.minDate) {
      if (dateValue < trimDate(this.minDate)) {
        errors.push(
          DATE_MIN_INVALID.replace("{date}", formatDate(this.minDate))
        );
      }
    }
    if (this.maxDate) {
      if (dateValue > trimDate(this.maxDate)) {
        errors.push(
          DATE_MAX_INVALID.replace("{date}", formatDate(this.maxDate))
        );
      }
    }
    return errors;
  }
}
