import { FC, HtmlHTMLAttributes, PropsWithChildren } from "react";

export type TitleProps = HtmlHTMLAttributes<HTMLHeadingElement> &
  PropsWithChildren;
export const Title: FC<TitleProps> = (props) => {
  return <h1 {...props} style={{ marginBottom: "12px" }} />;
};
