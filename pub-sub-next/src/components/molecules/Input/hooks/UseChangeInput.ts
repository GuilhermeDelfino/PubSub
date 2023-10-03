import {
  Dispatch,
  FormEvent,
  SetStateAction,
  useCallback,
  useState,
} from "react";
import { InputValidator } from "../validators";
import { InputFormatter } from "../formatters";
import styles from "../styles.module.css";

export const useChangeInput = ({
  validator,
  formatter,
  changeValue,
  className = "",
}: {
  validator?: InputValidator;
  formatter?: InputFormatter;
  changeValue(v: string): void;
  className?: string;
}) => {
  const [errors, setErrors] = useState<string[]>([]);
  const [date, setDate] = useState(new Date());

  const classNameInputError = errors.length > 0 ? styles.input_error : "";
  const classNameInput =
    styles.input + " " + className + " " + classNameInputError;
  return {
    date,
    setDate,
    errors,
    classNameInput,
    changeInput: useCallback(
      (ev: FormEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        let value = ev.currentTarget.value;
        if (formatter) {
          value = formatter.format(value);
        }
        if (validator) {
          const newErrors = validator.validate(value);
          setErrors(newErrors);
        }
        changeValue(value);
      },
      [changeValue, formatter, setErrors, validator]
    ),
    changeInputDate: useCallback(
      (date: Date | null) => {
        let value = date!.toString();
        if (formatter) {
          value = formatter.format(value);
        }
        if (validator) {
          const newErrors = validator.validate(value);
          setErrors(newErrors);
        }
        changeValue(value);
      },
      [changeValue, formatter, setErrors, validator]
    ),
  };
};
