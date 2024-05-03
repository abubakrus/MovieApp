package com.example.movieapp.domain.models


data class ActorsDomain(
    val peoples: List<PeopleDomain>,
    val crews: List<PeopleDomain>,
    val id: Int
)

