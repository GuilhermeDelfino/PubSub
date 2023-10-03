import regex from "@/util/regex";
import { InputFormatter } from "./InputFormatter";

export class InputNameFormatter implements InputFormatter {
  private static INSTANCE: null | InputNameFormatter = null;
  private constructor() {}
  static getInstance() {
    if (!this.INSTANCE) this.INSTANCE = new InputNameFormatter();
    return this.INSTANCE;
  }
  format(value: string): string {
    let name = value.replace(regex.CONTAINS_NUMBER_AND_SYMBOLS, "");
    return name;
  }
}
