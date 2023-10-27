import { createGlobalStyle } from "styled-components";

export const Styles = createGlobalStyle`
  * {
    margin: 0;
    padding: 0;
    outline: 0;
    box-sizing: border-box;
    font-family: "Inter", sans-serif;
  }

  body {
    margin: 0;
    padding: 0;

    width: 100vw;
    min-height: 100vh;
  }

  #root {
    display: flex;
    flex-direction: column;
  }
`;
