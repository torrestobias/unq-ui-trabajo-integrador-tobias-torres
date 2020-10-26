import React,{useEffect, useState} from "react";
import axios from 'axios';
import { useParams } from "react-router-dom";
import Navbar from "../Navbar/Navbar";
import SummaryNote from "../SummaryNote/SummaryNote";
import "../../styles/search.css";


function SearchCategory (){

    const [resSearchCategory, setResSeachCategory] = useState([]);
    
    let {name} = useParams()

    useEffect(() => {
        const getResultCategory = async () =>{
            axios.get("http://localhost:7000/category/"+name)
        .then(response =>{
            setResSeachCategory(response.data.Categories)
        })
        }
        getResultCategory();
        }, [name]
        
    )

    return(
        <div className="body-search">
            <Navbar/>
            <div className="body">
            <h2>Results found </h2>
            <div className="hola">
            {resSearchCategory.map(note => (
                    <SummaryNote key={note.id} note={note}/>
                    ))}
            </div>
            </div>
        </div>
    );
}

export default SearchCategory;