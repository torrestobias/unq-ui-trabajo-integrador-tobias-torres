import React,{useEffect, useState} from "react";
import { useParams } from "react-router-dom";
import Navbar from "../Navbar/Navbar";
import SummaryNote from "../SummaryNote/SummaryNote";
import axios from 'axios';

function SearchNote(){

    const [resSearch, setResSeach] = useState([]);
    
    let {search} = useParams()

    useEffect(() => {
        const getResult = async () =>{
            axios.get("http://localhost:7000/search?text="+search)
        .then(response =>{
            setResSeach(response.data.Notes)
        })
        }
        getResult();
        }, [search]
        
    )

    return(
        <div className="body-search">
            <Navbar/>
            <center><h1>Results found </h1></center>
            <div className="hola">
            {resSearch.map(note => (
                    <SummaryNote key={note.id} note={note}/>
                    ))}
            </div>
        </div>
    );
}

export default SearchNote;
