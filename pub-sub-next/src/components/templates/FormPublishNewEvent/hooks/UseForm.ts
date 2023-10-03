import { InputDateValidator } from "@/components/molecules/Input/validators";
import { InputTextValidator } from "@/components/molecules/Input/validators/InputTextValidator";
import { CreateEventDto } from "@/core/adapters/gateway/dtos/CreateEventDto";
import { FormEvent, useState } from "react";

import { Event } from "@/core/domain/entities/Event";
import strings from "@/util/strings";

export const UsePublishEventForm = ({
  publishEvent,
}: {
  publishEvent(dto: CreateEventDto): Promise<Event>;
}) => {
  const [description, setDescription] = useState("");
  const [location, setLocation] = useState("");
  const [author, setAuthor] = useState("");
  const [title, setTitle] = useState("");
  const [date, setDate] = useState("");

  const [isSuccess, setIsSuccess] = useState(false);
  const [generalError, setGeneralError] = useState("");
  const [createdEvent, setCreatedEvent] = useState<Event | null>(null);
  const [isLoading, setLoading] = useState(false);

  const inputValidatorDefault = new InputTextValidator(true, 3, 50);
  const inputValidatorDescription = new InputTextValidator(true, 3, 255);
  const inputValidatorEventDate = new InputDateValidator(true, new Date());

  const onSubmit = async (ev: FormEvent) => {
    setLoading(true);
    ev.preventDefault();

    const errorsDescription =
      inputValidatorDescription.validate(description).length === 0;
    const errorsLocation =
      inputValidatorDefault.validate(location).length === 0;
    const errorsAuthor = inputValidatorDefault.validate(author).length === 0;
    const errorsTitle = inputValidatorDefault.validate(title).length === 0;
    const errorsEventDate = inputValidatorEventDate.validate(date).length === 0;

    const hasNoErrors =
      errorsDescription &&
      errorsLocation &&
      errorsAuthor &&
      errorsTitle &&
      errorsEventDate;

    if (hasNoErrors) {
      const dto: CreateEventDto = {
        author,
        date: new Date(date),
        description,
        location,
        title,
      };

      try {
        const response = await publishEvent(dto);
        setCreatedEvent(response);
        setIsSuccess(true);
      } catch (error) {
        setGeneralError(strings.publishNewEvent.response.error);
        setIsSuccess(false);
      }
      cleanFields();
    }
    setLoading(false);
  };

  const cleanFields = () => {
    setDescription("");
    setLocation("");
    setAuthor("");
    setTitle("");
    setDate("");
  };

  return {
    inputValidatorDescription,
    inputValidatorEventDate,
    inputValidatorDefault,
    generalError,
    createdEvent,
    description,
    isLoading,
    isSuccess,
    location,
    author,
    title,
    date,
    setDescription,
    setLocation,
    setAuthor,
    setTitle,
    onSubmit,
    setDate,
  };
};
