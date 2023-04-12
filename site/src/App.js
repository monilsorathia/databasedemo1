import React, { useState } from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import MovieSearch from "./pages/MovieSearch";
import Montage from "./pages/Montage";
import LoginSignUp from "./pages/Login-SignUp";

function App() {
  const [email, setEmail] = useState("");

  return (
    <div>
      <Routes>
        {/* Root pages, located in /pages/ */}
        <Route path="/" element={<LoginSignUp setEmail={setEmail} />} />
        <Route path="/LoginSignUp" element={<LoginSignUp setEmail={setEmail} />} />
        <Route exact path="/MovieSearch" element={<MovieSearch email={email} />} />
        <Route exact path="/Montage" element={<Montage />} />
        {/* 404 page not found redirect */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </div>
  );
}

export default App;
