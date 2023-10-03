const regex = {
  VALID_NAME: /^[\D\s]{3,50}$/,
  VALID_EMAIL: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
  CONTAINS_STRING_AND_SPACES_AND_SYMBOLS: /^[\D\s]$/,
  CONTAINS_NUMBER_AND_SYMBOLS: /[\d.@!#$%*]/,
  CONTAINS_NUMBER: /[\d]/,
};
export default regex;
