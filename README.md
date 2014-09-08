hyepye
======

Armenian-English verb translation and conjugation application.

# Local and remote access commands

## PSQL - Local Main
`psql -d hyepye_main -U hyepyeuser -W`

## PSQL - Local Test
`psql -d hyepye_test -U hyepyeuser -W`

## PSQL - Remote
`heroku pg:psql`

## Run Tasks - Remote
`heroku run "sh target/bin/migrate history"`
