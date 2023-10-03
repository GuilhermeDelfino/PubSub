import { format } from "date-fns";

export const formatDate = (date: Date | string) => {
  if (typeof date === "string")
    return format(new Date(date), defaultFormatDate);
  return format(date, defaultFormatDate);
};
export const trimDate = (date: Date | string) =>
  typeof date === "string"
    ? new Date(new Date(date).toLocaleDateString())
    : new Date(date.toLocaleDateString());

export const defaultFormatDate = "dd/MM/yyyy";
export const defaultFormatDateTime = "dd/MM/yyyy, hh:mm";
