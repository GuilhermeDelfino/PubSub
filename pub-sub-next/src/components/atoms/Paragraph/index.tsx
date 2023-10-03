import { DetailedHTMLProps, FC, HTMLAttributes } from "react";
export type ParagraphProps = DetailedHTMLProps<
  HTMLAttributes<HTMLParagraphElement>,
  HTMLParagraphElement
>;
export const Paragraph: FC<ParagraphProps> = (props) => {
  return <p {...props} style={{ margin: "8px 0" }} />;
};
