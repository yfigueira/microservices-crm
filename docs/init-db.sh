#!/bin/bash

set -e  # Exit script on command error
set -u  # Exit script on undefined variable use

function create_database_and_user() {
  local user_name="$1"
  local user_password="$2"

  echo "  Creating database and user $user_name"

  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER "$user_name" WITH PASSWORD '$user_password';
    CREATE DATABASE "$user_name";
    GRANT ALL PRIVILEGES ON DATABASE "$user_name" TO "$user_name";
EOSQL

  echo "  Granting USAGE,CREATE on schema public in database $user_name"

  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$user_name" <<-EOSQL
    GRANT USAGE, CREATE ON SCHEMA public TO "$user_name";
EOSQL
}

if [[ -n "${POSTGRES_USERS_AND_DATABASES:-}" && -n "${POSTGRES_USER_PASSWORDS:-}" ]]; then
  echo "  Creating databases $POSTGRES_USERS_AND_DATABASES"

  IFS=',' read -ra USERS <<< "$POSTGRES_USERS_AND_DATABASES"
  IFS=',' read -ra PASSWORDS <<< "$POSTGRES_USER_PASSWORDS"

  if [[ "${#USERS[@]}" -ne "${#PASSWORDS[@]}" ]]; then
    echo "  Error: Number of users and passwords mismatch" >&2
    exit 1
  fi

  for i in "${!USERS[@]}"; do
    user="${USERS[$i]//[[:space:]]/}"
    pass="${PASSWORDS[$i]}"
    create_database_and_user "$user" "$pass"
  done

  echo "  Databases and users created"
else
  echo "  POSTGRES_USERS_AND_DATABASES or POSTGRES_USER_PASSWORDS not set or empty" >&2
fi
