import "./globals.css";
import { Open_Sans } from "next/font/google";
import { Header } from "@/components/organisms/header";
import { Metadata } from "next";

const openSans = Open_Sans({
  style: "normal",
  preload: true,
  subsets: ["latin"],
});
export const metadata: Metadata = {
  title: "Events.co",
  description: "Subscriber in Events",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="pt-br">
      <body className={openSans.className}>
        <Header />
        {children}
      </body>
    </html>
  );
}
