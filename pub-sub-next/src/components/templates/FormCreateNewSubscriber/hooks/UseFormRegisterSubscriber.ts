import { InputNameFormatter } from "@/components/molecules/Input/formatters";
import {
  InputEmailValidator,
  InputNameValidator,
} from "@/components/molecules/Input/validators";
import Email from "@/core/domain/VOs/Email";
import Name from "@/core/domain/VOs/Name";
import Subscriber from "@/core/domain/entities/Subscriber";
import { FormEvent, useState } from "react";

export const useFormRegisterSubscriber = ({
  createNewSubscriber,
}: {
  createNewSubscriber(sub: Subscriber): void;
}) => {
  const [isLoading, setLoading] = useState(false);
  const [isSuccess, setIsSuccess] = useState(false);
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");

  const inputEmailValidator = InputEmailValidator.getInstance();
  const inputNameValidator = InputNameValidator.getInstance();
  const inputNameFormatter = InputNameFormatter.getInstance();

  const onSubmit = (ev: FormEvent) => {
    setLoading(true);
    ev.preventDefault();
    const errorsName = inputNameValidator.validate(name);
    const errorsEmail = inputEmailValidator.validate(email);

    const hasNoErrors = errorsName.length === 0 && errorsEmail.length === 0;
    if (hasNoErrors) {
      const subscriber = new Subscriber(Name.of(name), Email.of(email));
      createNewSubscriber(subscriber);
      setEmail("");
      setName("");
    }
    setIsSuccess(true);
    setLoading(false);
  };

  return {
    inputNameFormatter,
    inputNameValidator,
    inputEmailValidator,
    isLoading,
    isSuccess,
    email,
    name,
    setEmail,
    onSubmit,
    setName,
  };
};
