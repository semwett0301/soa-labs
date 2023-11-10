import { formatWithOptions } from "date-fns/fp";
import { ru } from "date-fns/locale";

export const formatDateToRussianDate = formatWithOptions(
  { locale: ru },
  "dd.MM.yyyy"
);
