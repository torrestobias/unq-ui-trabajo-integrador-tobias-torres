import React from 'react';
import { Link } from 'react-router-dom';
import "../../styles/summaryNote.css";


function SummaryNote ({note}){
    
    const {id,categories} = note;



    return(
        <div className="container">
            
            <div className="summary-notes">
            <p className="text-summarynotes">{note.title}</p>
            <p className="text-summarynotes">Author: {note.author.name}</p>
            <p className="text-summarynotes">Categories:  
            {categories.map( category => (
                <Link to={"/category/"+category } key={category}>{category}</Link>
            )
            )}
            </p>
            
        <Link to={"/content/"+id} className="btn btn-info">Entrar a la nota</Link>
        </div>
        </div>
        )
}

export default SummaryNote;
