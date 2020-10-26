import React, {useEffect, useState} from "react";
import axios from 'axios';
import "bootstrap/dist/css/bootstrap.min.css";
import SummaryNote from "../SummaryNote/SummaryNote";
import Navbar from "../Navbar/Navbar";
import "../../styles/home.css";


function Home(){
    const [lastestNotes, setLatestNotes] = useState ([]);

    useEffect(() => {
        const getLatestNotes = async () =>{
            axios.get("http://localhost:7000/content")
        .then(response =>{
            setLatestNotes(response.data.latestContent)
        })
        }
        getLatestNotes();
        }, []
        
    )

    return (
        <div className="home">
            <Navbar/>
            <div className="title">
            <h1>Last Notes Uploaded</h1>
            </div>
            <div className="container">
                
                <div className="contenedor-notas">
                    {lastestNotes.map(note => (
                    <SummaryNote key={note.id} note={note}/>
                    ))}
                </div>
            </div>
        </div>
        )
}

export default Home;