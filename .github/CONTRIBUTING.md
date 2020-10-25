# How to contribute
## How to write a pull request

-Be polite and relaxed

-Take criticism as trying to learn from your mistakes not as that someone is trying to bully you

-Write a contructive summary about what you did and why it should be added

## Formatting of commits and PRs
(everything in the parentheses "<>" should be replaced

-Things added should be formatted like this: -added <thing> because of <feature request number>

-Things that are fixed should be formatted like this: -fixed <insert issue number if it exists> <short summary about what went wrong>

-Things that are removed should be formatted like this: -removed <thing> <reason>

-Things that are changed should be formatted like this: -changed <thing> <from> -> <to> <reason>

## How to make a new feature

Make a new branch on the indev branch, then fully implement everything u want. Lastly make a PR to the indev branch (we will probably accept that PR) it will be thoroughly tested again and if the whole thing is stable enough everything will go to the dev branch and ultimately to the master branch).

## What branch serves what purpose?

Master: Everything is as stable as it can be (these will be the releases)

Dev: pretty stable, made for people who want new features fast but don't care if there are any bugs in it.

Indev: This is the coding hellhole, everything here is only for testing. Bugs are everywhere and things are waiting to be transferred to the dev branch. tl;dr: This branch is unstable as hell and only used for developing new features.
